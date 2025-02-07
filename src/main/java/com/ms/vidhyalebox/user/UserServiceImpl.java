package com.ms.vidhyalebox.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ms.vidhyalebox.auth.JwtTokenProvider;
import com.ms.vidhyalebox.emailsender.EmailDetails;
import com.ms.vidhyalebox.emailsender.EmailService;
import com.ms.vidhyalebox.role.RoleEntity;
import com.ms.vidhyalebox.role.RoleRepo;
import com.ms.vidhyalebox.sharedapi.SignupRequestDTO;
import com.ms.vidhyalebox.sharedapi.generic.APiResponse;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.UnknownErrorException;

@Service
public class UserServiceImpl extends GenericService<GenericEntity, Long> implements IUserService  {

	@Value("${image.storage.path}")
	private String storagePath;  // Path where the images will be stored

	private final IUserRepo userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	private final UserMapperNormal userMapperNormal;
	private final RoleRepo roleRepo;
	private final EmailService emailService;

	public UserServiceImpl(IUserRepo userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserMapperNormal userMapperNormal, RoleRepo roleRepo, EmailService emailService) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
		this.userMapperNormal = userMapperNormal;
		this.roleRepo = roleRepo;
		this.emailService = emailService;
	}

	public GenericDTO signup(SignupRequestDTO signupRequestDTO) {
		// Encode the password
		signupRequestDTO.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
		// Fetch the RoleEntity by role name (e.g., "ROLE_SCHOOL_ADMIN")
		RoleEntity role = roleRepo.findByName(signupRequestDTO.getRole())
				.orElseThrow(() -> new IllegalArgumentException("Invalid role specified"));
		String admissionId = generateUniqueAdmissionId(signupRequestDTO.getSchoolName());
		UserEntity userEntity = (UserEntity) userMapperNormal.dtoToEntity(signupRequestDTO);
		userEntity.setIdentityProvider(admissionId);
		// Set the role in the OrgClientEntity
		userEntity.setRole(role.getName());

		// Save the OrgClientEntity with the assigned role
		UserEntity saveEntity = userRepository.save(userEntity);
		GenericDTO genericDTO = userMapperNormal.entityToDto(saveEntity);
		return genericDTO;
	}

	public boolean verifyUser(String token) {
		Optional<UserEntity> usere = userRepository.findByVerificationToken(token);
		if (usere.isPresent() && !usere.get().isVerified()) {
			UserEntity user = usere.get();
			user.setVerified(true);
			user.setVerificationToken(null); // Remove the token after verification
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<APiResponse<Object>> initiatePWDReset(String email) {
		String val = "";
		Optional<UserEntity> userop = null;
		try {
			userop = userRepository.findByEmailOrMobileNumber(email, null);
			if (userop.isPresent()) {
				UserEntity user = userop.get();
				String token = UUID.randomUUID().toString();
				user.setVerificationToken(token);
				user.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour

				userRepository.save(user);
				EmailDetails edetails = new EmailDetails();
				edetails.setRecipient(email);
				edetails.setSubject("VidyalayBox password reset mail");
				edetails.setMsgBody("Click to initiate password reset http://localhost:5173/forget-password?token="+token);
				val = emailService.sendSimpleMail(edetails);
			}
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APiResponse<>(
					"error",
					"Failed to send mail" + e.getLocalizedMessage() ,
					null,
					null
			));
		}

		APiResponse <Object> resp =  new APiResponse<>(
				userop.isPresent() && !val.contains("error") ?  "success" : "error",
				userop.isPresent() ? (val.contains("error") ? "Failed to send mail"  :  "Password reset link sent over mail" ) :  "User not found with the provided details",
				null,
				null
		);

		return  userop.isPresent() ? ResponseEntity.ok(resp) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
	}

	// Method to validate reset token and reset password
	public boolean validateResetToken(String token) {
		Optional<UserEntity> usere = userRepository.findByVerificationToken(token);

		return usere.isPresent() && usere.get().getExpiryDate().isAfter(LocalDateTime.now());
	}

	public APiResponse<Object> resetPassword(String token, String newPassword) {
		Optional<UserEntity> usere = null;
		try {
			usere = userRepository.findByVerificationToken(token);
			if (usere.isPresent() && !usere.get().getExpiryDate().isAfter(LocalDateTime.now())) {
				UserEntity user = usere.get();
				user.setVerificationToken(null);
				user.setPassword(passwordEncoder.encode(newPassword)); // You should hash the password
				userRepository.save(user);
			}
		} catch (Exception e) {
			throw new UnknownErrorException("Unknown error occurred, password reset failed");
		}
		return new APiResponse<>(
				usere.isPresent() ?  "success" : "error",
				usere.isPresent() ? "Password reset done" : "Token not found, password reset failed",
				null,
				null
		);
	}

	@Override
	public boolean isMobileNumberExist(String MobileNumber) {
		return false;
	}

	/*public boolean isMobileNumberExist(final String mobileNumber) {
		return userRepository.existsByMobileNumber(mobileNumber);
	}*/

   /* public UserEntity signup(UserEntity userEntity) {
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return userRepository.save(userEntity);
	}*/

	public String login(String phoneNumber, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(phoneNumber, password)
		);
		return jwtTokenProvider.generateToken(phoneNumber);
	}
	private String generateUniqueAdmissionId(String schoolName) {
		String admissionId;
		Random random = new Random();

		do {
			// Generate unique admission ID
			String prefix = (schoolName.length() >= 3 ? schoolName.substring(0, 3) : schoolName).toUpperCase();
			String currentYear = String.valueOf(java.time.Year.now().getValue());
			int randomNumber = 1000 + random.nextInt(9000);

			// Use text blocks with trim to remove newlines
			admissionId = """
            %s-%s-%04d
        """.formatted(prefix, currentYear, randomNumber).trim();

		} while (userRepository.existsByIdentityProvider(admissionId));

		return admissionId;
	}

	public String saveImage(MultipartFile file, String foldername) {
		// Generate a secure file name (sanitize input)
		String sanitizedFileName = sanitizeFileName(file.getOriginalFilename());

		// Create directory structure (e.g., storagePath/images/2025/02/02/filename.jpg)
		//	String dateDir = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new Date());
		Path directory = Paths.get(storagePath, foldername);
		Path filePath = null;
		try {
			if (!Files.exists(directory)) {
				Files.createDirectories(directory);
			}

			// Store the file (inside the generated directory)
			filePath = directory.resolve(sanitizedFileName);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			// Ensure file permissions (readable by the server, not publicly accessible need to uncomment in server)
			
			//setFilePermissions(filePath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return filePath.toString();
	}

	public Resource getImage(Long userId) {
		Optional<UserEntity> imageEntityOptional = userRepository.findById(userId);

		if (imageEntityOptional.isPresent()) {
			Path imagePath = Paths.get(imageEntityOptional.get().getImage());
			return new FileSystemResource(imagePath);
		}

		throw new RuntimeException("Image not found");
	}

	// Helper method to sanitize file names (avoid path traversal attacks)
	private String sanitizeFileName(String originalFileName) {
		String baseName = FilenameUtils.getBaseName(originalFileName);
		String extension = FilenameUtils.getExtension(originalFileName);

		// Generate a unique filename based on the current timestamp (this helps avoid collisions)
		String timestamp = String.valueOf(System.currentTimeMillis());
		return baseName + "_" + timestamp + "." + extension;
	}

	// Helper method to set secure file permissions (only readable by the server)
	private void setFilePermissions(Path filePath) throws IOException {
		Set<PosixFilePermission> permissions = new HashSet<>();
		permissions.add(PosixFilePermission.OWNER_READ);
		permissions.add(PosixFilePermission.OWNER_WRITE);
		permissions.add(PosixFilePermission.GROUP_READ);
		Files.setPosixFilePermissions(filePath, permissions);
	}

	public UserDetails loadUserByUsername(String username) {

		return null;
	}

	@Override
	public JpaRepository getRepo() {
		return userRepository;
	}

	@Override
	public IMapperNormal getMapper() {
		return userMapperNormal;
	}

	public boolean isEmailAlreadyExist(String emailAddress) {

		return false;
	}
}

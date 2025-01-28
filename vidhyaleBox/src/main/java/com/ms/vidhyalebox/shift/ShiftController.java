package com.ms.vidhyalebox.shift;

import com.ms.shared.api.auth.shiftDTO.ShiftDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/shift")
public class ShiftController extends GenericController<ShiftDTO, Long> {

    private final ShiftServiceImpl _shiftService;

    public ShiftController(final ShiftServiceImpl shiftService) {
        _shiftService = shiftService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return _shiftService;
    }
}
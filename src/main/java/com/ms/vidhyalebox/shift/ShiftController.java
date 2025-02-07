package com.ms.vidhyalebox.shift;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.shiftDTO.ShiftDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

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
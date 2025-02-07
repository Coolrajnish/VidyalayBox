package com.ms.vidhyalebox.addadmin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.OrgAdminSignupRequestDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@RequestMapping("/org_admin")
public class SchAdminController  extends GenericController<OrgAdminSignupRequestDTO , Long> {

    private final  SchAdminService schAdminService;

    public SchAdminController(SchAdminService schAdminService) {
        this.schAdminService = schAdminService;
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        return schAdminService;
    }
}

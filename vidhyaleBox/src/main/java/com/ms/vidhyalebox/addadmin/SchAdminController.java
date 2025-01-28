package com.ms.vidhyalebox.addadmin;

import com.ms.shared.api.auth.OrgAdminSignupRequestDTO;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

/*
 * The API Layer
 */

package com.thethelafaltein.casino.api;

import com.thethelafaltein.casino.service.CasinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/casino")
public class CasinoController {

    private final CasinoService casinoService;

    @Autowired
    public CasinoController(CasinoService casinoService) {
        this.casinoService = casinoService;
    }
}

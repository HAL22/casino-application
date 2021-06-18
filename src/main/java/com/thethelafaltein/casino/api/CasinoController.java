/*
 * The API Layer
 */

package com.thethelafaltein.casino.api;

import com.thethelafaltein.casino.model.Player;
import com.thethelafaltein.casino.model.Transaction;
import com.thethelafaltein.casino.service.CasinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/casino")
public class CasinoController {

    private final CasinoService casinoService;

    @Autowired
    public CasinoController(CasinoService casinoService) {
        this.casinoService = casinoService;
    }

    @GetMapping(path = "/transaction/balance/{playerId}/{transactionId}")
    public Double getBalance(@PathVariable("playerId") Long playerId,@PathVariable("transactionId") Long transactionId){
        return casinoService.getBalance(playerId,transactionId);
    }

    @PutMapping(path = "/transaction/deposit/{playerId}/{transactionId}")
    public void deposit(@PathVariable("playerId") Long playerId, @PathVariable("transactionId") Long transactionId, @RequestParam(required = false) Double amount){
        casinoService.deposit(playerId,transactionId,amount);
    }

    @PutMapping(path = "/transaction/deduct/{playerId}/{transactionId}")
    public void deduct(@PathVariable("playerId") Long playerId, @PathVariable("transactionId") Long transactionId, @RequestParam(required = false) Double amount){
        casinoService.deduct(playerId,transactionId,amount);
    }

    @PutMapping(path = "/player/transactions")
    public List<Transaction> getTransaction(@RequestParam(required = false) String username){
        return casinoService.getTransactions(username);
    }


}

package kg.neobis.fms.controllers;

import kg.neobis.fms.entity.Wallet;
import kg.neobis.fms.entity.enums.GroupStatus;
import kg.neobis.fms.exception.AlreadyExistException;
import kg.neobis.fms.exception.RecordNotFoundException;
import kg.neobis.fms.models.CategoryModel;
import kg.neobis.fms.models.GroupModel;
import kg.neobis.fms.models.WalletModel;
import kg.neobis.fms.services.GroupService;
import kg.neobis.fms.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyAuthority('READ_WALLET')")
@RestController
@RequestMapping("wallet")
@CrossOrigin
public class WalletController {

    private WalletService walletService;

    @Autowired
    WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("/getAllActiveWallets")
    public ResponseEntity<List<WalletModel>> getAllGroups(){
        List<WalletModel> list = walletService.getAllActiveWallets();
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAnyAuthority('ADD_WALLET')")
    @PostMapping("add")
    public ResponseEntity<String> addNewCategory(@RequestBody WalletModel model){
        try {
            walletService.addNewWallet(model);
            return ResponseEntity.ok("successfully added");
        } catch (AlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('UPDATE_WALLET')")
    @PutMapping("update")
    public ResponseEntity<String> updateCategory(@RequestBody WalletModel model){
        try {
            walletService.updateWallet(model);
            return ResponseEntity.ok("successfully updated");
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.bankinformation.webservices;

import com.example.bankinformation.objectmodel.BranchInfo;
import com.example.bankinformation.objectmodel.SearchCriteria;
import com.example.bankinformation.repository.dataaccess.BranchDAO;
import com.example.bankinformation.repository.dataobject.BranchDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankInformationController
{
  @Autowired
  BranchDAO branchInfoAccessService;

  public BankInformationController()
  {
    //nothing
  }

  @GetMapping(value="/branches")
  public ResponseEntity getBranchInfo(@RequestBody SearchCriteria criteria)
  {
    return ResponseEntity.ok("Get information about bank and their branches given the IFSC code. " + criteria.getBankName() + ", " + criteria.getCity() );
  }

  @GetMapping(value="/branch")
    public ResponseEntity getBranchInfo(@RequestParam(value="bankName") String bankName)
  {
    BranchInfo branchInfo = branchInfoAccessService.getBranchDetailsProvidedIfscCode(bankName);

    if (branchInfo == null)
    {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(branchInfo);
  }
}

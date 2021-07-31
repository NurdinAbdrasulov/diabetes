package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.Document;
import kg.neobis.diabetes.models.UserAgreementModel;
import kg.neobis.diabetes.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository repository;

    @Autowired
    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }


    public ResponseEntity<?> getUserAgreement() {
        Optional<Document> byId = repository.findById(1L);

        if(byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"соглашение не найдено!!!");

        Document document = byId.get();
        return ResponseEntity.ok( new UserAgreementModel(document.getText()));

    }
    public ResponseEntity<?> changeUserAgreement(UserAgreementModel model){
        Optional<Document> byId = repository.findById(1L);

        if(byId.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"соглашение не найдено!!!");

        Document document = byId.get();
        document.setText(model.getText());
        repository.save(document);
        return ResponseEntity.ok( new UserAgreementModel(document.getText()));
    }
}

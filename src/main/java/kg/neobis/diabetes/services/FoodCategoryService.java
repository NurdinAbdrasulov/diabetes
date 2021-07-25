package kg.neobis.diabetes.services;

import kg.neobis.diabetes.entity.FoodCategory;
import kg.neobis.diabetes.exception.RecordNotFoundException;
import kg.neobis.diabetes.models.FoodCategoryModel;
import kg.neobis.diabetes.models.ModelToAddFoodCategory;
import kg.neobis.diabetes.repositories.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryService {

    private final FoodCategoryRepository repository;
    private final FoodService foodService;

    @Autowired
    public FoodCategoryService(FoodCategoryRepository repository, FoodService foodService) {
        this.repository = repository;
        this.foodService = foodService;
    }

    public ResponseEntity<List<FoodCategoryModel>> getAll() {
        List<FoodCategory> all = repository.findAll();
        return ResponseEntity.ok(convertToModel(all));
    }

    public ResponseEntity<FoodCategoryModel> getById(Long id) throws RecordNotFoundException {
        Optional<FoodCategory> byId = repository.findById(id);
        if(byId.isEmpty())
            throw new RecordNotFoundException("нет категории с id " + id);
        return ResponseEntity.ok(convertToModel(byId.get()));
    }

    private List<FoodCategoryModel> convertToModel(List<FoodCategory> categories){
        List<FoodCategoryModel> result = new ArrayList<>();
        for(FoodCategory category: categories)
            result.add(new FoodCategoryModel(category.getId(), category.getName()));
        return result;
    }

    private FoodCategoryModel convertToModel(FoodCategory category){
        return new FoodCategoryModel(category.getId(), category.getName());
    }

    //add check if category with the same name exists
    public ResponseEntity<FoodCategoryModel> create(ModelToAddFoodCategory model) {
        FoodCategory category = new FoodCategory();
        category.setName(model.getName());
        return ResponseEntity.ok(convertToModel(repository.save(category)));
    }

    public ResponseEntity<FoodCategoryModel> updateById(ModelToAddFoodCategory model, Long id) {
        Optional<FoodCategory> byId = repository.findById(id);
        if (byId.isEmpty())
            throw new RecordNotFoundException("нет категории с id " + id);
        FoodCategory category = byId.get();
        category.setName(model.getName());
        FoodCategoryModel result = convertToModel(repository.save(category));
        return ResponseEntity.ok(result);
    }

    public void deleteById(Long id) {
        Optional<FoodCategory> byId = repository.findById(id);
        if (byId.isEmpty())
            throw new RecordNotFoundException("нет категории с id " + id);
        foodService.deleteCategory(byId.get());
        repository.deleteById(id);
    }
}

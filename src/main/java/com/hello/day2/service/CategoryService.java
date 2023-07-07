package com.hello.day2.service;

import com.hello.day2.enumclass.CateType;
import com.hello.day2.model.entity.Category;
import com.hello.day2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository cateRepository;

//    public List<Category> searchCategory() {
//        List<Category> categoryList = cateRepository.findAll();
//        return categoryList;
//    }

//    public List<Category> searchCategoryTop() {
//        List<Category> categoryList = cateRepository.findTop5ByOrderByRegDateDesc();
//        return categoryList;
//    }

    public List<Category> searchCategory(Map<String, String> param) {
        String categoryId = param.get("categoryId").trim();
        String name = param.get("name").trim();
        String status = param.get("status").trim();

        System.out.println("Categoryid:::"+categoryId);
        System.out.println("name:::"+name);
        System.out.println("status:::"+status);
        List<Category> CategoryList = new ArrayList<>();

//        if (checkNull(categoryId) && checkNull(name)) { // 아이디, 이름 모두 존재
//            System.out.println("아이디, 이름 모두 존재");
//            CategoryList = cateRepository.findCategoryByCategoryidAndNameLikeAndStatus(Categoryid, "%"+name+"%", Categorytatus.valueOf(status));
//        } else if (checkNull(categoryId) && !checkNull(name)) { // 아이디만 존재
//            System.out.println("아이디만 존재");
//            CategoryList = cateRepository.findCategoryByCategoryidLikeAndStatus("%" + Categoryid + "%", Categorytatus.valueOf(status));
//        }

        return CategoryList;
    }

    public boolean checkNull(String param) {
        Boolean check = false;
        if (param != null && param != "" && param.length() != 0) {
            check = true;
        }

        return check;
    }

//    public Category updateCategory(Map<String, String> param) throws Exception {
//        System.out.println("====================== updateCategory start ======================");
//        Category Category = cateRepository.findCategoryByCategoryid(param.get("Categoryid"));
//        try {
//            if (Category != null) {
//                Category.setStatus(Categorytatus.valueOf(param.get("status")));
//                Category.setHp(param.get("hp"));
//                Category.setEmail(param.get("email"));
//
//                Category = cateRepository.save(Category);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Category;
//    }

    public Category createCate(Map<String, String> param) {
        System.out.println(param.get("type")+"::"
                +param.get("title"+"::")
                +param.get("adminId"));
        CateType type = CateType.valueOf(param.get("type"));
        String title = param.get("title");
        LocalDateTime regDate = LocalDateTime.now();
        Long adminId = Long.valueOf(param.get("adminId"));
        Category category = Category.builder()
                .type(type)
                .title(title)
                .regDate(regDate)
                .adminId(adminId)
                .build();

        Category newCategory = cateRepository.save(category);

        return newCategory;
    }
}

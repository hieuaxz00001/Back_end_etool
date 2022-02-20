package com.rdsic.back_end_e_tool.service;



import com.rdsic.back_end_e_tool.entities.PageJson;
import com.rdsic.back_end_e_tool.specitication.SpecificationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BaseService<T> {
    // class này sử dụng JpaSpecificationExecutor để khởi chạy các tập điều kiện trong class SpecificationBuider
    // JpaSpecificationExecutor cung cấp 5 phương thức, việc của lớp service là lấy ra và sử dụng nó.
    protected final JpaSpecificationExecutor<T> repo;


    private final EntityManager entityManager;

    // hàm khởi tạo
    public BaseService(JpaSpecificationExecutor<T> repo, EntityManager entityManager) {
        this.repo = repo;
        this.entityManager = entityManager;
    }


    public T save(T data) throws Exception {
        return (T) ((JpaRepository) repo).save(data);
    }

    // thay vì phải dùng Object+Repository. findAll ta sẽ dùng lớp JpaRepository chính là lớp mà mỗi object đang kế thừa
    public T findById(Object id) throws Exception {
        return (T) ((JpaRepository) repo).findById(id).get();
    }

    public void delete(Object id) throws Exception {
        ((JpaRepository) repo).deleteById(id);
    }

    public PageJson<T> filterByPageWithSort(List<String> criteria, Pageable pageable, String sortCriteria) throws Exception {
        return resolveSort(sortCriteria)
                .map(sort -> new PageJson<>(repo.findAll(new SpecificationBuilder<T>().resolve(criteria), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort))))
                .orElse(new PageJson<>(repo.findAll(new SpecificationBuilder<T>().resolve(criteria), PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()))));
    }

    public int countPage(List<String> criteria, int size) throws Exception {
        return (int) (Math.ceil(repo.count(new SpecificationBuilder<T>().resolve(criteria)) / (size * 1.0)));
    }

    public List<T> filterWithSort(List<String> criteria, String sortCriteria) throws Exception {
        return resolveSort(sortCriteria)
                .map(sort -> repo.findAll(new SpecificationBuilder<T>().resolve(criteria), sort))
                .orElse(repo.findAll(new SpecificationBuilder<T>().resolve(criteria)));
    }

    private Optional<Sort> resolveSort(String sortCriteria) {
//        param của sort truyền vào dạng &sort="properties"-desc or -asc
        // phân tách chuỗi rồi gán vào đối tượng Sort
        System.out.println(sortCriteria);
        Sort sort;
        try {
            String[] path = sortCriteria.split("-");
            String field = path[0];
            sort = Sort.by(Sort.Direction.ASC, field);
            if (path.length > 1 && path[1].equalsIgnoreCase("desc")) {
                sort = Sort.by(Sort.Direction.DESC, field);
            }
            return Optional.of(sort);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

package com.rdsic.back_end_e_tool.specitication;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SpecificationBuilder<T> {
    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public void with(String key, String operation, Object value) {
        this.params.add(new SearchCriteria(key, operation, value));
    }

    public Specification<T> build() {
        if (params.isEmpty()) {
            return null;
        }
        // sau khi xử lý ở resolve() thì return ra builder.build()
        //
        List<Specification> specifications = params.stream()
                .map((Function<SearchCriteria, DataSpecification<T>>) DataSpecification::new)
                .collect(Collectors.toList());

        Specification result = specifications.get(0);
        // Specification result = Specification.where(specifications.get(0));
        //  Specification.where để xây dựng các tập điều kiện
        // điều kiện là "where" và "and" có cả "or"
        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specifications.get(i));
        }
        return result;
    }

    public Specification<T> resolve(List<String> params) {
        if (params == null) {
            params = new ArrayList<>();
        }
        SpecificationBuilder<T> builder = new SpecificationBuilder<>();
        Pattern pattern = Pattern.compile("(.*)(>=|<=|<<|!=|==|%|:|=|<|>)(.*)");
        for (String param : params) {
            Matcher matcher = pattern.matcher(param);
            // matcher.find() kiểm tra các chuỗi con có trùng với pattern trên hay không
            // nếu find() trả về true tức là trong param của query có ký tự trùng, sẽ tiến hành phân chia từng param con một theo SearchCriteria
            // group(3) sẽ trả về giá trị của SearchCriteria.value
            // group(2) sẽ trả về giá trị của SearchCriteria.opera
            // group(1) sẽ trả về giá trị của SearchCriteria.key
            // các tham số này này sẽ được dùng trong builder()
            // mỗi lần buider.width() nó lại add các param nhỏ trong một query  vào List<SearchCriteria> đến khi hết
            // vd: params: id=1,hoVaTen=hieu nó sẽ cắt ra làm 2 param truyền vào list
            // true, false ở trong with() chỉ dùng khi tìm kiếm các trường boolean.
            if (matcher.find())
                if (matcher.group(3).equalsIgnoreCase("true"))
                    builder.with(matcher.group(1), matcher.group(2), true);
                else if (matcher.group(3).equalsIgnoreCase("false"))
                    builder.with(matcher.group(1), matcher.group(2), false);
                else
                    builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }
}

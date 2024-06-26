package org.zerock.springex.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor // 빌더를 위한 모든 생성자
@NoArgsConstructor // 파라미터 수집을 위한 기본 생성자
public class TodoDTO {

    private Long tno;

    @NotEmpty
    private String title;

    @Future
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty
    private String writer; // 작성자, 새로 추가된 값
}

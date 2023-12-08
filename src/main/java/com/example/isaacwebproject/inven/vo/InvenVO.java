package com.example.isaacwebproject.inven.vo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvenVO {
        private String memId;
        private int ItemId;
        private int amount;
        private int used;
}

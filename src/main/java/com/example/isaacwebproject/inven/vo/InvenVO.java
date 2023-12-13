package com.example.isaacwebproject.inven.vo;
import java.util.List;

import jakarta.annotation.Nullable;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvenVO {
        private String memId;
        private int ItemId;
        private int amount;
        private String img_url;

        public InvenVO(String memId, int itemsId, int amount) {
        }
}

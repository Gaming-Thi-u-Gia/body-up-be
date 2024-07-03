package com.bodyupbe.bodyupbe.model.workout_video;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DailyRecipeId implements Serializable {
    private Integer id;
    private String part;
}

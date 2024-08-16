package com.seohyeon.bookloan.image;

import com.seohyeon.bookloan.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origFileName;
    private String fileName;

    public Image(String origFileName, String fileName) {
        this.origFileName = origFileName;
        this.fileName = fileName;
    }
}

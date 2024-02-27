package org.choongang.file.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.commons.entities.BaseMember;

@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
public class FileInfo extends BaseMember {
}

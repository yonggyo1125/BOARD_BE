package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.commons.entities.BaseMember;
import org.choongang.member.constants.Authority;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseMember {

    @Id
    @Column(length=45)
    private String bid;

    @Column(length=60, nullable = false)
    private String bName;

    private boolean active;

    private int pagePerRows = 20;

    private int pageRanges = 10;

    @Lob
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable = false)
    private Authority writeAuthority = Authority.ALL;

    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable = false)
    private Authority listAuthority = Authority.ALL;

    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable = false)
    private Authority viewAuthority = Authority.ALL;

    @Enumerated(EnumType.STRING)
    @Column(length=10, nullable = false)
    private Authority commentAuthority = Authority.ALL;
}

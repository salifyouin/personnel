package ci.dgmp.personnel.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strId;
    private String strCode;
    private String strSigle;
    private String strLibelle;
    private Boolean strStatut;
    private long strLevel;
    @ManyToOne
    @JoinColumn(name = "str_tutelle_directe_Id") @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Structure tutelleDirecte;
    @ManyToOne
    @JoinColumn(name = "strtypId")
    private Type type;
    public Structure(Long strId) {
        this.strId = strId;
    }
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString()
    {
        return strSigle + " : " + strLibelle ;
    }

    public Structure(Long strId, String strCode, String strSigle, String strLibelle, Structure tutelleDirecte, Type type) {
        this.strId = strId;
        this.strCode = strCode;
        this.strSigle = strSigle;
        this.strLibelle = strLibelle;
        this.tutelleDirecte = tutelleDirecte;
        this.type = type;
    }

    @Transient
    List<Structure> structuresSousTutelle;
}

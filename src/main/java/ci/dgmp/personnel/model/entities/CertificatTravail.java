package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("certTravail")
public class CertificatTravail extends DemandeActe {
    private String libelleCertificat;
}

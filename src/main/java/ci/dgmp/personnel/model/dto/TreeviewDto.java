package ci.dgmp.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreeviewDto
{
   private String text;
   private String href;
   private String tags;
   private List<TreeviewDto> nodes;
}

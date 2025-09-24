package br.gov.serpro.rtc.api.model.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "URL para download de arquivo")
public class DownloadUrlOutput {

    @Schema(description = "URL para download do arquivo", example = "https://exemplo.com/arquivo.zip")
    private String downloadUrl;
}

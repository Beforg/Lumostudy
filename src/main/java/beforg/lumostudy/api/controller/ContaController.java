package beforg.lumostudy.api.controller;

import beforg.lumostudy.api.domain.response.ResponseDTO;
import beforg.lumostudy.api.service.ContaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Value("${photo.storage.path}")
    private String photoStoragePath;

    @PostMapping("/{cod}/foto")
    public ResponseEntity<ResponseDTO> uploadFoto(@PathVariable String cod, @RequestParam("foto")MultipartFile foto) {
        this.contaService.uploadFoto(cod, foto);
        return ResponseEntity.ok().body(new ResponseDTO("Foto enviada com sucesso", HttpStatus.OK.toString()));
    }
    @GetMapping("/img/{cod}")
    public ResponseEntity<byte[]> getImage(@PathVariable String cod) {
        try {
            Resource pathResource = new ClassPathResource("images/" + cod + ".jpg");
            if (!pathResource.exists()) {
                pathResource = new ClassPathResource("images/" + cod + ".png");
            }
            if (!pathResource.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            String contentType = Files.probeContentType(pathResource.getFile().toPath());
            byte[] imgBytes = Files.readAllBytes(pathResource.getFile().toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);
            return new ResponseEntity<>(imgBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

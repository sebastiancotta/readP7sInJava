import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.demoiselle.signer.policy.impl.cades.AttachedContentValidation;
import org.demoiselle.signer.policy.impl.cades.pkcs7.impl.CAdESChecker;

public class Main {

    public static void main(String[] args) throws Exception {

        Main.Read_PKCS7_Cert("C:\\Users\\Teletrabalho\\Desktop\\PIPE_ANEXO_CADES.p7s");
    }

    public static void Read_PKCS7_Cert(String cert_file) throws IOException {

        Path path = Paths.get(cert_file);
        byte[] attachedSignature = Files.readAllBytes(path);
        /* implementar metodo de leitura de arquivo */

        CAdESChecker checker = new CAdESChecker();

        /* Para extrair o conteudo original validando a assinatura */

        AttachedContentValidation content =  checker.getAttached(attachedSignature, true);
        byte[] contentOri = content.getExtractedContent();
//        System.out.println(Base64.getEncoder().encodeToString(contentOri));
//        System.out.println(Base64.getDecoder().decode(contentOri));
        OutputStream out = new FileOutputStream("teste.pdf");
        out.write(Base64.getDecoder().decode(contentOri));
        out.flush();
        out.close();
//        System.out.println(Base64.getEncoder().encodeToString(contentOri));


    }

    
}

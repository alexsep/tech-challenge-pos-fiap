package br.com.fiap.techchallenge.infrastructure.gateways;

import br.com.fiap.techchallenge.application.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.infrastructure.persistence.util.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class PagamentoGatewayImpl implements PagamentoGateway {


    @Override
    public String gerarQrCode(String idPedido, BigDecimal valor) {

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            Map<String, String> dadosQrCode = new HashMap<>();
            dadosQrCode.put("valor", valor.toString());
            dadosQrCode.put("idPedido", idPedido);

            String dadosQrCodeString = new ObjectMapper().writeValueAsString(dadosQrCode);

            BitMatrix qrCodeEncoded =
                    qrCodeWriter.encode(dadosQrCodeString, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(qrCodeEncoded, "PNG", pngOutputStream);
            byte[] byteArray = pngOutputStream.toByteArray();

            return Base64.getEncoder().encodeToString(byteArray);
        } catch (Exception e) {
            throw new BusinessException("Não foi possível gerar o QRCode. Tente novamente.");
        }


    }
}

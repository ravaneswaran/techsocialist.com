package com.techsocialist.plugin.pg;

import com.techsocialist.plugin.pg.impl.AbstractPaytmPaymentGatewayRestPlugin;
import com.techsocialist.plugin.pg.paytm.request.BalanceInfoRequest;
import com.techsocialist.plugin.pg.paytm.request.InitiateTransactionRequest;

import java.io.IOException;

public class PaytmPaymentGatewayRestPlugin extends AbstractPaytmPaymentGatewayRestPlugin {

    @Override
    public String initiateTransaction(String merchantId, String merchantKey, String userId, long amount, String currency, String websiteName, String callbackUrl) throws IOException {
        return this.initiateTransaction(merchantId, merchantKey, "C11", "WEB", "v1", userId, amount, currency, websiteName, callbackUrl);
    }

    @Override
    public String initiateTransaction(String merchantId, String merchantKey, String clientId, String channelId, String version, String userId, long amount, String currency, String websiteName, String callbackUrl) throws IOException {
        InitiateTransactionRequest paytmRequest = new InitiateTransactionRequest();

        paytmRequest.setMerchantId(merchantId);
        paytmRequest.setMerchantKey(merchantKey);
        paytmRequest.setClientId(clientId);
        paytmRequest.setChannelId(channelId);
        paytmRequest.setVersion(version);

        paytmRequest.setUserId(userId).setAmount(amount).setCurrency(currency).setCallbackUrl(callbackUrl).setWebsiteName(websiteName);

        String jsonResponse = processPaytmRequest(paytmRequest.url(false), "POST", "application/json", paytmRequest.dataAsJsonString());

        return jsonResponse;
    }

    @Override
    public String balanceInfo(String merchantId, String merchantKey, String transactionToken, String paymentMode) throws IOException {
        return this.balanceInfo(merchantId, merchantKey, "C11", "WEB", "v1", transactionToken, paymentMode);
    }

    @Override
    public String balanceInfo(String merchantId, String merchantKey, String clientId, String channelId, String version, String transactionToken, String paymentMode) throws IOException {
        BalanceInfoRequest paytmRequest = new BalanceInfoRequest();

        paytmRequest.setMerchantId(merchantId);
        paytmRequest.setMerchantKey(merchantKey);
        paytmRequest.setClientId(clientId);
        paytmRequest.setChannelId(channelId);
        paytmRequest.setVersion(version);

        paytmRequest.setPaymentMode(paymentMode);

        String jsonResponse = processPaytmRequest(paytmRequest.url(false), "POST", "application/json", paytmRequest.dataAsJsonString());

        return jsonResponse;
    }
}
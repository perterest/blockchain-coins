package com.hippo.wallet.transaction;

import io.eblock.eos4j.OfflineSign;
import io.eblock.eos4j.api.vo.SignParam;
import com.hippo.foundation.utils.NumericUtil;
import com.hippo.wallet.Wallet;
import com.hippo.wallet.model.TokenException;

/**
 * Created by pie on 2019-03-05 23: 22.
 */
public class EOSCreateAccount implements TransactionSigner {

    private Long buyRam;
    private String newAccount;
    private String creator;
    private SignParam signParam;
    private String newAccountPubKey;


    public EOSCreateAccount(SignParam signParam, String creator, String newAccount, String newAccountPubKey,Long buyRam) {
        this.signParam = signParam;
        this.creator=creator;
        this.newAccount=newAccount;
        this.buyRam=buyRam;
        this.newAccountPubKey=newAccountPubKey;
    }

    @Override
    public TxSignResult signTransaction(String chainId, String password, Wallet wallet) {
        String pubKey = wallet.getKeyPathPrivates().get(0).getPublicKey();
        String priKey = NumericUtil.bytesToHex(wallet.decryptPrvKeyFor(pubKey, password));
        OfflineSign sign = new OfflineSign();
        try {
           String content= sign.createAccount(signParam, priKey, creator,newAccount,newAccountPubKey,newAccountPubKey, buyRam);
            return new TxSignResult(content, "");
        } catch (Exception e) {
            throw new TokenException(String.format("签名失败 原因:%s",e.getMessage()));
        }
    }
}

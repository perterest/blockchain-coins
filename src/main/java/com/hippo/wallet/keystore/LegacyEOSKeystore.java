package com.hippo.wallet.keystore;

import com.google.common.base.Strings;
import com.hippo.foundation.crypto.Crypto;
import com.hippo.wallet.model.ChainType;
import com.hippo.wallet.model.KeyPair;
import com.hippo.wallet.model.Metadata;
import com.hippo.wallet.model.TokenException;
import com.hippo.wallet.transaction.EOSKey;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Deprecated
public class LegacyEOSKeystore extends V3Keystore {

    public LegacyEOSKeystore() {
    }

    public static LegacyEOSKeystore create(Metadata metadata, String accountName, String password, String prvKeyHex) {
        return new LegacyEOSKeystore(metadata, accountName, password, prvKeyHex, "");
    }

    public List<KeyPair> exportPrivateKeys(String password) {
        byte[] decrypted = decryptCiphertext(password);
        String wif = new String(decrypted);
        EOSKey key = EOSKey.fromWIF(wif);
        KeyPair keyPair = new KeyPair();
        keyPair.setPrivateKey(wif);
        keyPair.setPublicKey(key.getPublicKeyAsHex());
        return Collections.singletonList(keyPair);
    }


    @Deprecated
    public LegacyEOSKeystore(Metadata metadata, String address, String password, String prvKeyHex, String id) {

        if (!metadata.getChainType().equals(ChainType.EOS)) {
            throw new TokenException("Only init eos keystore in this constructor");
        }
        byte[] prvKeyBytes = prvKeyHex.getBytes();
        this.address = address;
        this.crypto = Crypto.createPBKDF2Crypto(password, prvKeyBytes);
        metadata.setWalletType(Metadata.V3);
        this.metadata = metadata;
        this.version = VERSION;
        this.id = Strings.isNullOrEmpty(id) ? UUID.randomUUID().toString() : id;
    }
}

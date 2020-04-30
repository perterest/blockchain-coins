package com.hippo.wallet.transaction;

import com.hippo.wallet.Wallet;

public interface TransactionSigner {
  TxSignResult signTransaction(String chainId, String password, Wallet wallet);
}

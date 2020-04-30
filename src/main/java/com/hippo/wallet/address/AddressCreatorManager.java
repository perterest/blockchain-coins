package com.hippo.wallet.address;

import com.hippo.wallet.model.ChainType;
import com.hippo.wallet.model.Metadata;
import com.hippo.wallet.model.TokenException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import com.hippo.wallet.model.Messages;
import com.hippo.wallet.network.LitecoinMainNetParams;

public class AddressCreatorManager {

  public static AddressCreator getInstance(String type, boolean isMainnet, String segWit) {
    if (ChainType.ETHEREUM.equals(type)) {
      return new EthereumAddressCreator();
    }else if(ChainType.LITECOIN.equals(type)){
      NetworkParameters network= LitecoinMainNetParams.get();
      return new BitcoinAddressCreator(network);
    } else if (ChainType.BITCOIN.equals(type)) {

      NetworkParameters network = isMainnet ? MainNetParams.get() : TestNet3Params.get();
      if (Metadata.P2WPKH.equals(segWit)) {
        return new SegWitBitcoinAddressCreator(network);
      }
      return new BitcoinAddressCreator(network);
    } else {
      throw new TokenException(Messages.WALLET_INVALID_TYPE);
    }
  }

}

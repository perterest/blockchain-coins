package com.hippo.wallet.keystore;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hippo.foundation.crypto.EncPair;
import com.hippo.wallet.model.Metadata;

/**
 * Created by xyz on 2018/2/8.
 */
public abstract class V3Ignore {
  @JsonIgnore
  public abstract EncPair getEncMnemonic();

  @JsonIgnore
  @JsonGetter(value = "imTokenMeta")
  public abstract Metadata getMetadata();

  @JsonIgnore
  public abstract String getMnemonicPath();

  @JsonIgnore
  public abstract String getEncXPub();

  @JsonIgnore
  public abstract int getMnemonicIndex();
}

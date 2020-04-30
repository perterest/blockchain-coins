package com.hippo.wallet.keystore;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hippo.wallet.model.Metadata;

public abstract class IMTKeystore extends WalletKeystore {
  @JsonIgnore
  Metadata metadata;

  @JsonGetter(value = "imTokenMeta")
  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  public IMTKeystore() {
    super();
  }
}

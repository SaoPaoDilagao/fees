package com.nttdata.fees.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  /**
   * Fees status.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class FeeStatus {
    public static final int PENDING = 1;
    public static final int PAYED = 2;
  }

  }

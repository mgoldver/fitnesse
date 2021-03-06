// Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
// Released under the terms of the CPL Common Public License version 1.0.
package fitnesse.slim.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fitnesse.util.StringUtils;

import fitnesse.slim.Converter;
import fitnesse.slim.SlimError;

public class DateConverter implements Converter<Date> {
  public final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

  public String toString(Date o) {
    return o != null ? dateFormat.format(o) : NULL_VALUE;
  }

  public Date fromString(String arg) {
    if (StringUtils.isBlank(arg))
      return null;
    try {
      return dateFormat.parse(arg);
    } catch (ParseException e) {
      throw new SlimError("Can't parse date " + arg, e);
    }
  }
}

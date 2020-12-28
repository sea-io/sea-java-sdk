public class UriUtil {
  private void appendSchemeSpecificPart(StringBuffer sb,
                                            String opaquePart,
                                            String authority,
                                            String userInfo,
                                            String host,
                                            int port,
                                            String path,
                                            String query)
      {
          if (opaquePart != null) {

              if (opaquePart.startsWith("//[")) {
                  int end =  opaquePart.indexOf("]");
                  if (end != -1 && opaquePart.indexOf(":")!=-1) {
                      String doquote, dontquote;
                      if (end == opaquePart.length()) {
                          dontquote = opaquePart;
                          doquote = "";
                      } else {
                          dontquote = opaquePart.substring(0,end+1);
                          doquote = opaquePart.substring(end+1);
                      }
                      sb.append (dontquote);
                      sb.append(quote(doquote, L_URIC, H_URIC));
                  }
              } else {
                  sb.append(quote(opaquePart, L_URIC, H_URIC));
              }
          } else {
              appendAuthority(sb, authority, userInfo, host, port);
              if (path != null)
                  sb.append(quote(path, L_PATH, H_PATH));
              if (query != null) {
                  sb.append('?');
                  sb.append(quote(query, L_URIC, H_URIC));
              }
          }
      }

      private void appendAuthority(StringBuffer sb,
                                   String authority,
                                   String userInfo,
                                   String host,
                                   int port)
      {
          if (host != null) {
              sb.append("//");
              if (userInfo != null) {
                  sb.append(quote(userInfo, L_USERINFO, H_USERINFO));
                  sb.append('@');
              }
              boolean needBrackets = ((host.indexOf(':') >= 0)
                                      && !host.startsWith("[")
                                      && !host.endsWith("]"));
              if (needBrackets) sb.append('[');
              sb.append(host);
              if (needBrackets) sb.append(']');
              if (port != -1) {
                  sb.append(':');
                  sb.append(port);
              }
          } else if (authority != null) {
              sb.append("//");
              if (authority.startsWith("[")) {
                  // authority should (but may not) contain an embedded IPv6 address
                  int end = authority.indexOf("]");
                  String doquote = authority, dontquote = "";
                  if (end != -1 && authority.indexOf(":") != -1) {
                      // the authority contains an IPv6 address
                      if (end == authority.length()) {
                          dontquote = authority;
                          doquote = "";
                      } else {
                          dontquote = authority.substring(0 , end + 1);
                          doquote = authority.substring(end + 1);
                      }
                  }
                  sb.append(dontquote);
                  sb.append(quote(doquote,
                              L_REG_NAME | L_SERVER,
                              H_REG_NAME | H_SERVER));
              } else {
                  sb.append(quote(authority,
                              L_REG_NAME | L_SERVER,
                              H_REG_NAME | H_SERVER));
              }
          }
      }private static String quote(String s, long lowMask, long highMask) {
          int n = s.length();
          StringBuffer sb = null;
          boolean allowNonASCII = ((lowMask & L_ESCAPED) != 0);
          for (int i = 0; i < s.length(); i++) {
              char c = s.charAt(i);
              if (c < '\u0080') {
                  if (!match(c, lowMask, highMask)) {
                      if (sb == null) {
                          sb = new StringBuffer();
                          sb.append(s.substring(0, i));
                      }
                      appendEscape(sb, (byte)c);
                  } else {
                      if (sb != null)
                          sb.append(c);
                  }
              } else if (allowNonASCII
                         && (Character.isSpaceChar(c)
                             || Character.isISOControl(c))) {
                  if (sb == null) {
                      sb = new StringBuffer();
                      sb.append(s.substring(0, i));
                  }
                  appendEncoded(sb, c);
              } else {
                  if (sb != null)
                      sb.append(c);
              }
          }
          return (sb == null) ? s : sb.toString();
      }
   }

 public static void encode() { 
 2         String name = "I am james"; 
 3         toHex(name.toCharArray()); 
 4         try { 
 5             byte[] iso8859 = name.getBytes("ISO-8859-1"); 
 6             toHex(iso8859); 
 7             byte[] gb2312 = name.getBytes("GB2312"); 
 8             toHex(gb2312); 
 9             byte[] gbk = name.getBytes("GBK"); 
10             toHex(gbk); 
11             byte[] utf16 = name.getBytes("UTF-16"); 
12             toHex(utf16); 
13             byte[] utf8 = name.getBytes("UTF-8"); 
14             toHex(utf8); 
15         } catch (UnsupportedEncodingException e) { 
16             e.printStackTrace(); 
17         } 
18  } 
if (bb > 0xff) {    // DoubleByte 
 2             if (dl - dp < 2) 
 3                 return CoderResult.OVERFLOW; 
 4             da[dp++] = (byte) (bb >> 8); 
 5             da[dp++] = (byte) bb; 
 6  } else {                      // SingleByte 
 7             if (dl - dp < 1) 
 8                 return CoderResult.OVERFLOW; 
 9             da[dp++] = (byte) bb; 
10  } 
//try it for t
private CoderResult encodeArrayLoop(CharBuffer src, 
 2  ByteBuffer dst){ 
 3             char[] sa = src.array(); 
 4             int sp = src.arrayOffset() + src.position(); 
 5             int sl = src.arrayOffset() + src.limit(); 
 6             byte[] da = dst.array(); 
 7             int dp = dst.arrayOffset() + dst.position(); 
 8             int dl = dst.arrayOffset() + dst.limit(); 
 9             int dlASCII = dp + Math.min(sl - sp, dl - dp); 
10             // ASCII only loop 
11             while (dp < dlASCII && sa[sp] < '\u0080') 
12                 da[dp++] = (byte) sa[sp++]; 
13             while (sp < sl) { 
14                 char c = sa[sp]; 
15                 if (c < 0x80) { 
16                     // Have at most seven bits 
17                     if (dp >= dl) 
18                         return overflow(src, sp, dst, dp); 
19                     da[dp++] = (byte)c; 
20                 } else if (c < 0x800) { 
21                     // 2 bytes, 11 bits 
22                     if (dl - dp < 2) 
23                         return overflow(src, sp, dst, dp); 
24                     da[dp++] = (byte)(0xc0 | (c >> 6)); 
25                     da[dp++] = (byte)(0x80 | (c & 0x3f)); 
26                 } else if (Character.isSurrogate(c)) { 
27                     // Have a surrogate pair 
28                     if (sgp == null) 
29                         sgp = new Surrogate.Parser(); 
30                     int uc = sgp.parse(c, sa, sp, sl); 
31                     if (uc < 0) { 
32                         updatePositions(src, sp, dst, dp); 
33                         return sgp.error(); 
34                     } 
35                     if (dl - dp < 4) 
36                         return overflow(src, sp, dst, dp); 
37                     da[dp++] = (byte)(0xf0 | ((uc >> 18))); 
38                     da[dp++] = (byte)(0x80 | ((uc >> 12) & 0x3f)); 
39                     da[dp++] = (byte)(0x80 | ((uc >>  6) & 0x3f)); 
40                     da[dp++] = (byte)(0x80 | (uc & 0x3f)); 
41                     sp++;  // 2 chars 
42                 } else { 
43                     // 3 bytes, 16 bits so can do it!
44                     if (dl - dp < 3) 
45                         return overflow(src, sp, dst, dp); 
46                     da[dp++] = (byte)(0xe0 | ((c >> 12))); 
47                     da[dp++] = (byte)(0x80 | ((c >>  6) & 0x3f)); 
48                     da[dp++] = (byte)(0x80 | (c & 0x3f)); 
49                 } 
50                 sp++; 
51             } 
52             updatePositions(src, sp, dst, dp); 
53             return CoderResult.UNDERFLOW; 
54  } 
protected void convertURI(MessageBytes uri, Request request) 
 2  throws Exception { 
 3         ByteChunk bc = uri.getByteChunk(); 
 4         int length = bc.getLength(); 
 5         CharChunk cc = uri.getCharChunk(); 
 6         cc.allocate(length, -1); 
 7         String enc = connector.getURIEncoding(); 
 8         if (enc != null) { 
 9             B2CConverter conv = request.getURIConverter(); 
10             try { 
11                 if (conv == null) { 
12                     conv = new B2CConverter(enc); 
13                     request.setURIConverter(conv); 
14                 } 
15             } catch (IOException e) {...} 
16             if (conv != null) { 
17                 try { 
18                     conv.convert(bc, cc, cc.getBuffer().length - 
19  cc.getEnd()); 
20                     uri.setChars(cc.getBuffer(), cc.getStart(), 
21  cc.getLength()); 
22                     return; 
23                 } catch (IOException e) {...} 
24             } 
25         } 
26         // Default encoding: fast conversion 
27         byte[] bbuf = bc.getBuffer(); 
28         char[] cbuf = cc.getBuffer(); 
29         int start = bc.getStart(); 
30         for (int i = 0; i < length; i++) { 
31             cbuf[i] = (char) (bbuf[i + start] & 0xff); 
32         } 
33         uri.setChars(cbuf, 0, length); 
34  } 

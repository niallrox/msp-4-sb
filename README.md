# MSP4SB

1. Для своей программы из лабораторной работы #4 по дисциплине "Программирование интернет-приложений" реализовать:
MBean, считающий общее число установленных пользователем точек, а также число точек, попадающих в область. В случае, если количество установленных пользователем точек стало кратно 5, разработанный MBean должен отправлять оповещение об этом событии.
MBean, определяющий средний интервал между кликами пользователя по координатной плоскости.
2. С помощью утилиты JConsole провести мониторинг программы:
Снять показания MBean-классов, разработанных в ходе выполнения задания 1.
Определить время (в мс), прошедшее с момента запуска виртуальной машины.
3. С помощью утилиты VisualVM провести мониторинг и профилирование программы:
Снять график изменения показаний MBean-классов, разработанных в ходе выполнения задания 1, с течением времени.
Определить имя класса, объекты которого занимают наибольший объём памяти JVM; определить пользовательский класс, в экземплярах которого находятся эти объекты.
4. Получить HeapDump, и с помощью утилиты VisualVM локализовать и устранить "утечку памяти" в программе ниже:

```
// var. 12333
public class Lab4 {
  public static void main(String[] args) {
    D a = new D();
    D b = new C();
    C c = new C();
    b.m11();
    b.m12();
    a.m13();
    b.m29();
    c.m40();
    a.m17();
    b.m47();
    a.m32();
    c.m34();
    b.m42();
    a.m9(a);
    c.m9(b);
    c.m9(c);
    c.m21();
    c.m37();
    c.m22();
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
             C d = new C();
             d.m7();
             Thread.sleep(9);
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
}
class D {
  int m4;
  int m2;
  int m31;
  int m39;
  int m30;
  long m27;
  long m16;
  long m49;
  java.io.ObjectOutputStream helloWorldWrtr;
  java.io.ObjectOutputStream testStream;
  java.io.ObjectOutputStream printStrm;
  int[] m41 = {1, 1, -3, -3, 2};
  int[] m36 = {1, 2, 2, -1, 2};
  int[] m48 = {2, 2, -3, 3, 0};
  static java.util.Map<java.net.URI,byte[]> cache = new java.util.HashMap<>();
  static int m25;
  static int m50;
  static int m8;
  static int m18;
  static int m19;
  java.util.List<String> m33 = new java.util.ArrayList<>();
  java.util.List<String> m43 = new java.util.ArrayList<>();
  java.util.List<String> m45 = new java.util.ArrayList<>();
  public D() {
    m4 = 7;
    m2 = 6;
    m31 = 3;
    m39 = 2;
    m30 = 1;
    m27 = 6L;
    m16 = 8L;
    m49 = 4L;
    try {
        helloWorldWrtr = new java.io.ObjectOutputStream(new java.io.FileOutputStream("helloWorldWrtr.txt"));
        testStream = new java.io.ObjectOutputStream(new java.io.FileOutputStream("testStream.txt"));
        printStrm = new java.io.ObjectOutputStream(new java.io.FileOutputStream("printStrm.txt"));
    } catch (java.lang.Exception e) {
      // Do nothing
    }
  }
  public void init() {
    try {
      if (helloWorldWrtr == null) helloWorldWrtr = new java.io.ObjectOutputStream(new java.io.FileOutputStream("helloWorldWrtr.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", helloWorldWrtr = " + helloWorldWrtr);
    } catch(Exception e) {
      // Ignore it
    }
    try {
      if (testStream == null) testStream = new java.io.ObjectOutputStream(new java.io.FileOutputStream("testStream.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", testStream = " + testStream);
    } catch(Exception e) {
      // Ignore it
    }
    try {
      if (printStrm == null) printStrm = new java.io.ObjectOutputStream(new java.io.FileOutputStream("printStrm.txt"));
      System.out.println("Thread : " + Thread.currentThread() + ", printStrm = " + printStrm);
    } catch(Exception e) {
      // Ignore it
    }
  }
  public byte[] getValueFromCache(String s) {
    try {
      java.net.URI url = new java.net.URI(s);
      if(!cache.containsKey(url)) {
        cache.put(url, new byte[1048576]);
      }
      return cache.get(url);
    } catch (Exception e) {
      System.out.println("Error: invalid URL!");
      return null;
    }
  }
  public void m11() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldWrtr) {
              helloWorldWrtr.writeObject("метод m11 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              helloWorldWrtr.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m12() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(printStrm) {
              printStrm.writeObject("метод m12 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(5);
              printStrm.flush();
              printStrm.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m13() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(printStrm) {
              printStrm.writeObject("метод m13 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(5);
              printStrm.flush();
              printStrm.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m29() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldWrtr) {
              helloWorldWrtr.writeObject("метод m29 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              helloWorldWrtr.flush();
              helloWorldWrtr.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m40() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(printStrm) {
              printStrm.writeObject("метод m40 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(5);
              printStrm.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m17() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(helloWorldWrtr) {
              helloWorldWrtr.writeObject("метод m17 в классе D (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              helloWorldWrtr.flush();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public static void m47() {
    System.out.println("метод m47 в классе D");
    System.out.println(m25);
  }
  public static void m32() {
    System.out.println("метод m32 в классе D");
    System.out.println((m25 - 3));
  }
  public static void m34() {
    System.out.println("метод m34 в классе D");
    System.out.println(m50);
  }
  public static void m42() {
    System.out.println("метод m42 в классе D");
    System.out.println((m50 - 5));
  }
  public void m9(D r) {
    r.m11();
  }
  public void m9(C r) {
    r.m12();
  }
}
class C extends D {
  public C() {
    m4 = 9;
    m2 = 1;
    m31 = 3;
    m30 = 4;
  }
  public void m11() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(testStream) {
              testStream.writeObject("метод m11 в классе C (#" + String.valueOf(i) + ")");
              Thread.sleep(6);
              testStream.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m12() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(printStrm) {
              printStrm.writeObject("метод m12 в классе C (#" + String.valueOf(i) + ")");
              Thread.sleep(8);
              printStrm.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m13() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(printStrm) {
              printStrm.writeObject("метод m13 в классе C (#" + String.valueOf(i) + ")");
              Thread.sleep(12);
              printStrm.flush();
              printStrm.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m17() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        init();
        int i = 0;
        while(true) {
          i++;
          try {
            synchronized(printStrm) {
              printStrm.writeObject("метод m17 в классе C (#" + String.valueOf(i) + ")");
              Thread.sleep(9);
              printStrm.reset();
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m21() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              Thread.sleep(9);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m37() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              Thread.sleep(8);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public void m22() {
    Thread t = new Thread(new Runnable() {
      public void run() {
        while(true) {
          try {
            synchronized(cache) {
              getValueFromCache("https://www.google.com");
              Thread.sleep(13);
            }
          } catch(Exception e) {
            // Do nothing
          }
        }
      }
    });
    t.start();
  }
  public static void m47() {
    System.out.println("метод m47 в классе C");
    System.out.println(--m25);
  }
  public static void m32() {
    System.out.println("метод m32 в классе C");
    System.out.println(m50);
  }
  public static void m34() {
    System.out.println("метод m34 в классе C");
    System.out.println((m50 + 2));
  }
  public static void m42() {
    System.out.println("метод m42 в классе C");
    System.out.println(m50);
  }
  public void m7() {
    for(int i = 0; i < 6; i++) {
      this.m33.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.m33.size());
    }
  }
  public void m15() {
    for(int i = 0; i < 5; i++) {
      this.m43.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.m43.size());
    }
  }
  public void m38() {
    for(int i = 0; i < 9; i++) {
      this.m33.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.m33.size());
    }
  }
  public void m28() {
    for(int i = 0; i < 6; i++) {
      this.m33.add(String.valueOf(System.nanoTime()));
      // System.out.println(this.m33.size());
    }
  }
  public void m9(D r) {
    r.m13();
  }
  public void m9(C r) {
    r.m29();
  }
}
```

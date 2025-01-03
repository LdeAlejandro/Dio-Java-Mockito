# mockito-exemplos
Exemplos de utilização do Mockito em projetos Java

- https://www.baeldung.com/mockito-spy
- https://www.dio.me/articles/mockito-como-utilizar-de-maneira-simples


@Mock
@InjectMocks
@Captor
@Test
@Spy


mockito core
mockito inline (para metodos estaticos e  variables finals)

#### **@Mock**
- Cria um mock para uma classe ou interface.
- Usado para simular dependências externas.
- **Exemplo:**
  ```java
  @Mock
  private MeuServico servico;
  ```

---

#### **@InjectMocks**
- Injeta automaticamente mocks em um objeto real.
- Útil para testar a lógica de um objeto que depende de outras classes.
- **Exemplo:**
  ```java
  @InjectMocks
  private MeuControlador controlador;
  ```

---

#### **@Captor**
- Cria um captor para capturar valores passados a métodos simulados.
- Usado para verificar valores reais usados nos mocks.
- **Exemplo:**
  ```java
  @Captor
  private ArgumentCaptor<String> captor;
  ```

---

#### **@Test**
- Indica que o método é um teste de unidade.
- Executa métodos com asserções e comportamento esperado.
- **Exemplo:**
  ```java
  @Test
  void meuTeste() {
      assertEquals(1, 1);
  }
  ```

---

#### **@Spy**
- Cria um espião para um objeto real.
- Permite chamar métodos reais enquanto simula comportamentos específicos.
- **Exemplo:**
  ```java
  @Spy
  private MeuServico servicoReal;
  ```

---

### **Dependências Necessárias**

#### **Core Mockito**
- Usado para mocks e spies básicos.
- Inclui as anotações `@Mock`, `@InjectMocks`, `@Captor`, `@Spy`.

#### **Mockito Inline**
- Necessário para mockar métodos estáticos e variáveis finais.
- Adicionar dependência:
  ```xml
  <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-inline</artifactId>
      <version>5.x.x</version>
      <scope>test</scope>
  </dependency>
  ``` 






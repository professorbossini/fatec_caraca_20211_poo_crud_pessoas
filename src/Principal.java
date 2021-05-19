import javax.swing.JOptionPane;

public class Principal {
  public static void main(String[] args) {
    String menu = "1-Cadastrar\n2-Atualizar\n3-Apagar\n4-Listar\n0-Sair";
    //PessoaDAO dao = new PessoaDAO();
    int op;
    do{
      op = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch (op){
        case 1:{
          String nome = JOptionPane.showInputDialog("Digite o nome");
          String fone = JOptionPane.showInputDialog("Digite o fone");
          String email = JOptionPane.showInputDialog("Digite o email");
          if (PessoaDAO.inserir(new Pessoa(nome, fone, email))){
            JOptionPane.showMessageDialog(null, "OK, inseriu");
          }
          else{
            JOptionPane.showMessageDialog(null, "NOK, tente novamente mais tarde");
          }
          
          break;
        }
        case 2:{
          String nome = JOptionPane.showInputDialog("Digite o nome");
          String fone = JOptionPane.showInputDialog("Digite o fone");
          String email = JOptionPane.showInputDialog("Digite o email");
          int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código"));
          if (PessoaDAO.atualizar(new Pessoa(codigo, nome, fone, email))) {
        	  JOptionPane.showMessageDialog(null, "Ok, atualizou");
          }
          else {
        	  JOptionPane.showMessageDialog(null, "NOK, Falhou");
          }
          break;
        }
        case 3:{
        	int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código"));
        	JOptionPane.showMessageDialog(
        			null, 
        			PessoaDAO.remover(new Pessoa(codigo)) ? "Ok, removeu" : "NOK, falhou"
			);
        	break;
        }
        case 4:{
        	String resultado = PessoaDAO.listar();
        	JOptionPane.showMessageDialog(null, resultado != null ? resultado : "NOK, Falhou");
        	break;
        }
        case 0:
          JOptionPane.showMessageDialog(null, "Até logo");
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção inválida");
      }

    }while (op != 0);
  }  
}

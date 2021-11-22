public class WrongInputDataException extends IllegalArgumentException{

  public WrongInputDataException(){
    super();
  }

  public WrongInputDataException(String s){
    super(s);
  }

  public WrongInputDataException(String s, Throwable cause){
    super(s, cause);
  }

}

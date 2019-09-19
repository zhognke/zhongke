package com.example.busniess.exception;

import com.example.busniess.resultpackage.CodeMsg;
import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class MyException extends Exception{


private CodeMsg codeMsg;
public MyException(CodeMsg codeMsg){
    super(codeMsg.toString());
    this.codeMsg=codeMsg;

}


}

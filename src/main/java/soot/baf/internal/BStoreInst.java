package soot.baf.internal;

/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 1999 Patrick Lam, Patrick Pominville and Raja Vallee-Rai
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.util.Collections;
import java.util.List;

import soot.AbstractJasminClass;
import soot.Local;
import soot.Type;
import soot.UnitPrinter;
import soot.ValueBox;
import soot.baf.InstSwitch;
import soot.baf.StoreInst;
import soot.util.Switch;

public class BStoreInst extends AbstractOpTypeInst implements StoreInst {

  ValueBox localBox;
  List defBoxes;

  public BStoreInst(Type opType, Local local) {
    super(opType);
    localBox = new BafLocalBox(local);
    defBoxes = Collections.singletonList(localBox);
  }

  public int getInCount() {
    return 1;
  }

  public Object clone() {
    return new BStoreInst(getOpType(), getLocal());
  }

  public int getInMachineCount() {
    return AbstractJasminClass.sizeOfType(getOpType());
  }

  public int getOutCount() {
    return 0;
  }

  public int getOutMachineCount() {
    return 0;
  }

  final public String getName() {
    return "store";
  }

  final String getParameters() {
    return " " + localBox.getValue().toString();
  }

  protected void getParameters(UnitPrinter up) {
    up.literal(" ");
    localBox.toString(up);
  }

  public void apply(Switch sw) {
    ((InstSwitch) sw).caseStoreInst(this);
  }

  public void setLocal(Local l) {
    localBox.setValue(l);
  }

  public Local getLocal() {
    return (Local) localBox.getValue();
  }

  public List getDefBoxes() {
    return defBoxes;
  }

}
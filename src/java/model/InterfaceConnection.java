/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author maciej
 */
public class InterfaceConnection {
        public DeviceInterface input;
        public DeviceInterface output;
        public InterfaceConnection(DeviceInterface input, DeviceInterface output) {
            this.input = input;
            this.output = output;
        }
        public boolean isValid() {
            if (input == output) {
                return false;
            }
            
            if (input.getInterfaceDirection() == DeviceInterface.InterfaceDirection.Output) {
                return false;
            }
            
            if (output.getInterfaceDirection() == DeviceInterface.InterfaceDirection.Input) {
                return false;
            }
            
            if (!output.getDataType().equals(input.getDataType())) {
                return false;
            }
            
            return true;
        }
        public String log() {
            return this.output.getParentDevice().getId() + "." + this.output.getId() + " -> " + this.input.getParentDevice().getId() + "." + this.input.getId();
        }
    }

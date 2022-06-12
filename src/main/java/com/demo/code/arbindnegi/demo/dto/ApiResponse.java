/**
 * 
 */
package com.demo.code.arbindnegi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiResponse {

    private String message;
    private boolean status;
}

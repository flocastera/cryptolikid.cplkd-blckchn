package lbp.toolsfordev.blckchn.node.models;

import lombok.*;

import java.io.Serializable;

/**
 * ResponseMessage
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** message */
	private String message;

}

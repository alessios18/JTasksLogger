/**
 * 
 */
package it.alessios.jtaskslogger.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author alessio
 *
 */
public class IdAdapter extends XmlAdapter<Integer, Integer> {

	@Override
	public Integer unmarshal(Integer v) throws Exception {
		IdStacker.getInstance().incrementIdStack(v);
		return v;
	}

	@Override
	public Integer marshal(Integer v) throws Exception {
		return v;
	}

	
}
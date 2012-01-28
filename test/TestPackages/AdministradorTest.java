/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackages;

/**
 *
 * @author hector
 */

import Logica.Administrador;
import junit.framework.TestCase;

public class AdministradorTest extends TestCase {

    final Administrador administrador = new Administrador(1, "AdminTest", 
            "Hector", "Sam", "123456","ssam_88@gmail.com");

	public AdministradorTest() 
	{
		
	}

   
	public void testGetApellido()
	{
		assertEquals("Sam", administrador.getApellido());
	}

        public void testGetNombre()
	{
		assertEquals("Hector", administrador.getNombre());
	}

        public void testGetId()
	{
		assertEquals(1, administrador.getId());
	}
        public void testGetNick()
	{
		assertEquals("AdminTest", administrador.getNick());
	}
        public void testGetPassword()
	{
		assertEquals("123456", administrador.getPassword());
	}

        public void testGetEmail()
        {
            assertEquals("ssam_88@gmail.com", administrador.getEmail());
        }

 }
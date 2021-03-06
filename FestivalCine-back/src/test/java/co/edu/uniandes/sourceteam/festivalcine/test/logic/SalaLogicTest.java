/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.test.logic;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ISillaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ITeatroLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.SalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.SillaLogic;
import co.edu.uniandes.sourceteam.festivalcine.ejbs.TeatroLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SalaPersistence;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SillaPersistence;
import co.edu.uniandes.sourceteam.festivalcine.persistence.TeatroPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ya.bejarano10
 */
@RunWith(Arquillian.class)
public class SalaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ISalaLogic salaLogic;
    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<SalaEntity> data = new ArrayList<SalaEntity>();
    private List<SillaEntity> sillasData = new ArrayList<>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TeatroLogic.class.getPackage())
                .addPackage(ITeatroLogic.class.getPackage())
                .addPackage(TeatroPersistence.class.getPackage())
                .addPackage(TeatroEntity.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
                .addPackage(SalaLogic.class.getPackage())
                .addPackage(ISalaLogic.class.getPackage())
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SillaLogic.class.getPackage())
                .addPackage(ISillaLogic.class.getPackage())
                .addPackage(SillaPersistence.class.getPackage())
                .addPackage(SillaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from SillaEntity").executeUpdate();
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from TeatroEntity").executeUpdate();
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        TeatroEntity teatro = factory.manufacturePojo(TeatroEntity.class);
        em.persist(teatro);
       for (int i = 0; i < 3; i++) 
        {
            SillaEntity sillaActual = factory.manufacturePojo(SillaEntity.class);
            em.persist(sillaActual);
            sillasData.add(sillaActual);
        }
        
        for (int i = 0; i < 3; i++) 
        {
           SalaEntity entity = factory.manufacturePojo(SalaEntity.class);
            entity.setTeatro(teatro);
            entity.setSillas(sillasData);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Company con un nombre que no existe
     * @throws java.lang.Exception
     */
    @Test
    public void createSalaTest1() 
    {
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
       
        SalaEntity result = salaLogic.createSala(newEntity);
        Assert.assertNotNull(result);

        SalaEntity entity = em.find(SalaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());

    }

    /**
     * Prueba para crear un Company con un nombre que ya existe
     * @throws java.lang.Exception
     */
    @Test
    public void createSalaTest2() 
    {
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
        newEntity.setName(data.get(0).getName());
        SalaEntity result = salaLogic.createSala(newEntity);
    }

   // @Test
    //public void getSalasTest() {
      //  List<SalaEntity> list = salaLogic.getSalas();
        //Assert.assertEquals(data.size(), list.size());
        //for (SalaEntity entity : list) {
          //  boolean found = false;
            //for (SalaEntity storedEntity : data) {
              //  if (entity.getId().equals(storedEntity.getId())) {
                //    found = true;
                //}
            //}
            //Assert.assertTrue(found);
        //}
    //}

    @Test
    public void getSalaTest() {
        SalaEntity entity = data.get(0);
        SalaEntity resultEntity = salaLogic.getSala(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Company
     *
     *
     */
    @Test
    public void deleteSalaTest() {
        SalaEntity entity = data.get(1);
        salaLogic.deleteSala(entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Company
     *
     *
     */
    @Test
    public void updateSalaTest() {
        SalaEntity entity = data.get(0);
        SalaEntity pojoEntity = factory.manufacturePojo(SalaEntity.class);

        pojoEntity.setId(entity.getId());

        salaLogic.updateSala(pojoEntity);

        SalaEntity resp = em.find(SalaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
}

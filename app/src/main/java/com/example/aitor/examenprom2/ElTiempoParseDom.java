package com.example.aitor.examenprom2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ElTiempoParseDom {
        private URL rssUrl;

        public  ElTiempoParseDom (String url)
        {
            try
            {
                this.rssUrl = new URL(url);
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }
        }

        public List<Pronostico> parse()
        {
            //Instanciamos la fábrica para DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            List<Pronostico> temperaturas = new ArrayList<Pronostico>();

            try
            {
                //Creamos un nuevo parser DOM
                DocumentBuilder builder = factory.newDocumentBuilder();

                //Realizamos lalectura completa del XML
                Document dom = builder.parse(this.getInputStream());

                //Nos posicionamos en el nodo principal del árbol Aemet
                Element root = dom.getDocumentElement();

                //Localizamos todos los elementos <temperatura>
                NodeList items = root.getElementsByTagName("hora");

                //Recorremos la lista de previsiones
                for (int i=0; i<items.getLength(); i++)
                {
                    Pronostico pronostico = new Pronostico();

                    //Obtenemos la temperatura actual
                    Node item = items.item(i);

                    //Obtenemos la lista de datos de la temperatura actual
                    NodeList datosTemperatura = item.getChildNodes();
                    //Procesamos cada dato temperatura
                    for (int j=0; j<datosTemperatura.getLength(); j++)
                    {
                        Node dato = datosTemperatura.item(j);
                        String etiqueta = dato.getNodeName();

                        if (etiqueta.equals("temperatura"))
                        {
                            String texto = obtenerTexto(dato);
                            pronostico.setTemperatura(Integer.parseInt(texto));
                        }
                        else if (etiqueta.equals("hora_datos"))
                        {
                            String texto = obtenerTexto(dato);
                            pronostico.setHora(texto);
                        }

                    }

                    temperaturas.add(pronostico);
                }
            }
            catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }

            return temperaturas;
        }

        private String obtenerTexto(Node dato)
        {
            StringBuilder texto = new StringBuilder();
            NodeList fragmentos = dato.getChildNodes();

            for (int k=0;k<fragmentos.getLength();k++)
            {
                texto.append(fragmentos.item(k).getNodeValue());
            }

            return texto.toString();
        }

        private InputStream getInputStream()
        {
            try
            {
                return rssUrl.openConnection().getInputStream();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
}


package jobs;

import java.util.Date;

import models.Problema;
import models.Pessoa;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		if (Problema.count() == 0) {
			Problema internet = new Problema("Internet", 100);
			internet.save();
			
			Problema hardware = new Problema("Hardware", 101);
			hardware.save();
			
			Problema software = new Problema("Software", 102);
			software.save();
			
			Problema SO = new Problema("Sistema Operacional", 103);
			SO.save();
			
			Pessoa joao = new Pessoa();
			joao.nome = "João da Silva";
			joao.email = "joaossilva@gmail.com";
			joao.problema = SO;
			joao.save();
		}
	}

}
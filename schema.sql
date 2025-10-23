-- Tabelas para Oficina Automotiva (português)
CREATE TABLE IF NOT EXISTS condutor (
  id IDENTITY PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  cnh VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS veiculo (
  id IDENTITY PRIMARY KEY,
  vin VARCHAR(50),
  placa VARCHAR(20),
  marca VARCHAR(100),
  modelo VARCHAR(100),
  ano INT,
  tipo VARCHAR(50),
  condutor_id BIGINT,
  FOREIGN KEY(condutor_id) REFERENCES condutor(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS registro_servico (
  id IDENTITY PRIMARY KEY,
  veiculo_id BIGINT NOT NULL,
  descricao VARCHAR(500) NOT NULL,
  data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  custo DECIMAL(10,2),
  FOREIGN KEY(veiculo_id) REFERENCES veiculo(id) ON DELETE CASCADE
);

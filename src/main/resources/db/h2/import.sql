INSERT INTO sources (path, type, is_random, black_list, black_url, description) VALUES ('stoloto', 'META', true, '192.145.*.*', 'http://yandex.ru', null);

INSERT INTO destinations (id, source_path, domain, url, is_default) VALUES ('1ff1adc6-2e66-4492-943a-ca2f863337ad', 'stoloto', 'www.stoloto.ru', 'http://www.stoloto.ru/?ad=justlink&utm_source=justlink&utm_medium=cpa&utm_campaign=test', false);
INSERT INTO destinations (id, source_path, domain, url, is_default) VALUES ('bcc0ee05-8359-4d93-b30e-bc10a3e97df9', 'stoloto', 'www.stoloto.ru', 'http://www.stoloto.ru/?ad=justlink&utm_source=justlink&utm_medium=cpa&utm_campaign=test', true);
INSERT INTO destinations (id, source_path, domain, url, is_default) VALUES ('657b4123-5701-4d65-ac75-c9b0b04aa9b6', 'stoloto', 'www.stoloto.ru', 'http://www.stoloto.ru/?ad=justlink&utm_source=justlink&utm_medium=cpa&utm_campaign=test', false);
INSERT INTO destinations (id, source_path, domain, url, is_default) VALUES ('f27b9061-3e46-40bf-b32d-30fb8609b1d4', 'stoloto', 'www.stoloto.ru', 'http://www.stoloto.ru/?ad=justlink&utm_source=justlink&utm_medium=cpa&utm_campaign=test', true);
INSERT INTO destinations (id, source_path, domain, url, is_default) VALUES ('e8392af3-1346-4a7c-ac21-7f7c44618c02', 'stoloto', 'ad.admitad.com', 'http://ad.admitad.com/g/hwow701jphf361bf8f3b0e4677dd22', false);
INSERT INTO destinations (id, source_path, domain, url, is_default) VALUES ('f0b7e0c9-73af-4813-bd29-54cd60d4f217', 'stoloto', 'ad.admitad.com', 'http://ad.admitad.com/g/hwow701jphf361bf8f3b0e4677dd22', true);


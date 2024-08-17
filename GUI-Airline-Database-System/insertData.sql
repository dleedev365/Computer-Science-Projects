insert into Airport
select 'YAA','Anahim Lake Airport','Anahim Lake' union
select 'ZEL','Denny Island Aerodrome','Bella Bella' union
select 'YBD','Bella Coola Airport','Bella Coola' union
select 'YBL','Campbell River Airport','Campbell River' union
select 'YQQ','CFB Comox','Comox' union
select 'YXC','Canadian Rockies Intl Airport','Cranbrook' union
select 'YLW','Kelowna International Airport','Kelowna' union
select 'ZMT','Masset Airport','Masset' union
select 'YZT','Port Hardy Airport','Port Hardy' union
select 'YPW','Powell River Airport','Powell River' union
select 'YXS','Prince George Airport','Prince George' union
select 'YVR','Vancouver Intl Airport','Vancouver' union
select 'YYJ','Victoria Intl Airport','Victoria' union
select 'YWL','Williams Lake Airport','Williams Lake' union
select 'YPR','Prince Rupert Airport','Prince Rupert' union
select 'YWS','Green Lake Water Aerodrome','Whistler';
insert into Flight
select 'JA100',291,'YVR','YXC'union
select'JA110',185,'YVR','YWL'union
select'JA120',212,'YVR','YAA'union
select'JA130',232,'YVR','YBD'union
select'JA140',64,'YVR','YPW'union
select'JA150',93,'YVR','YBL'union
select'JA160',74,'YVR','YQQ'union
select'JA170',185,'YVR','YZT'union
select'JA180',442,'YVR','ZMT'union
select'JA190',34,'YVR','YYJ'union
select'JA200',177,'YYJ','YLW'union
select'JA210',316,'YYJ','YXS'union
select'JA220',95,'YZT','ZEL'union
select'JA230',141,'YLW','YXC'union
select'JA240',21,'YQQ','YBL'union
select'JA250',173,'YBL','ZEL'union
select'JA260',48,'YBD','YAA';
insert into Flight_Instance
select 'JA100','11/28/16','D9',6 union
select'JA100','11/30/16','B4',5 union
select'JA110','11/28/16','B10',1 union
select'JA110','11/30/16','D9',3 union
select'JA110','12/02/16','A1',1 union
select'JA130','11/29/16','B7',3 union
select'JA130','12/01/16','B4',2 union
select'JA140','11/28/16','B3',4 union
select'JA140','11/30/16','B7',4 union
select'JA140','12/02/16','D8',3 union
select'JA160','11/27/16','D9',2 union
select'JA160','11/28/16','B9',3 union
select'JA170','11/27/16','B9',3 union
select'JA170','11/29/16','B9',5 union
select'JA170','12/02/16','B8',4 union
select'JA180','11/28/16','A4',5 union
select'JA190','11/27/16','A7',1 union
select'JA190','11/28/16','D9',2 union
select'JA190','11/29/16','B4',1 union
select'JA190','11/30/16','B3',2 union
select'JA190','12/01/16','B6',1 union
select'JA190','12/02/16','B3',2 union
select'JA190','12/03/16','B8',1 union
select'JA200','11/28/16','A3',7 union
select'JA200','11/30/16','B3',6 union
select'JA200','12/02/16','B3',6 union
select'JA210','11/29/16','B2',8 union
select'JA210','12/01/16','A2',5 union
select'JA220','11/28/16','A2',8 union
select'JA220','11/30/16','A3',7 union
select'JA230','11/28/16','A2',9 union
select'JA230','11/30/16','A2',8 union
select'JA230','12/02/16','B2',7 union
select'JA240','11/27/16','A2',4 union
select'JA240','11/29/16','A3',6 union
select'JA240','12/02/16','B1',8 union
select'JA250','11/28/16','A3',10 union
select'JA250','12/02/16','B2',9 union
select'JA260','11/29/16','A3',7 union
select'JA260','12/01/16','A3',6;
insert into Passenger
select 10839,'Amy','Hayes',0 union
select 11696,'Denise','Lopez',0 union
select 11790,'Benjamin','Green',0 union
select 11799,'Judith','Martinez',0 union
select 13230,'Marie','Brooks',0 union
select 13423,'Norma','Simmons',0 union
select 13697,'Charles','Hill',0 union
select 13874,'Jimmy','Cooper',0 union
select 14295,'Anne','Ramirez',0 union
select 16837,'Stephen','Hughes',0 union
select 18166,'Ruby','Barnes',0 union
select 19308,'Mildred','Reed',0 union
select 19973,'Albert','Kelly',0 union
select 20287,'Eugene','Thomas',0 union
select 22050,'Helen','Sanchez',0 union
select 23010,'Martha','Young',0 union
select 25052,'Jack','Anderson',0 union
select 25159,'Shirley','Brooks',0 union
select 27004,'Steven','Johnson',0 union
select 27954,'Diana','Gonzales',0 union
select 28453,'Margaret','White',0 union
select 28505,'Joe','Cook',0 union
select 29474,'Amanda','White',0 union
select 29652,'Martha','Butler',0 union
select 30525,'Helen','Carter',0 union
select 30622,'Harry','Murphy',0 union
select 30807,'Roy','Morris',0 union
select 32422,'Christine','Gray',0 union
select 33133,'Henry','Barnes',0 union
select 33726,'Jerry','Cook',0 union
select 33850,'Henry','Ward',0 union
select 33913,'Ronald','Bell',0 union
select 34069,'Earl','Lee',0 union
select 35059,'Larry','Murphy',0 union
select 35380,'Harold','Clark',0 union
select 35780,'Harold','Young',0 union
select 36238,'Ralph','Morgan',0 union
select 36978,'Johnny','Hall',0 union
select 37088,'Cynthia','Ross',0 union
select 37716,'Annie','Scott',0 union
select 38003,'Joshua','Griffin',0 union
select 38351,'Victor','Perez',0 union
select 38602,'Robin','Jones',0 union
select 38861,'Gerald','James',0 union
select 40351,'Sandra','Hayes',0 union
select 41545,'Terry','Bailey',0 union
select 41648,'Shawn','Clark',0 union
select 42612,'Joe','Kelly',0 union
select 43010,'Anthony','Bailey',0 union
select 43705,'Louis','Bell',0 union
select 44065,'Benjamin','Ramirez',0 union
select 44459,'Gerald','Watson',0 union
select 44637,'Susan','Carter',0 union
select 44922,'Dennis','Flores',0 union
select 45029,'Steve','Ward',0 union
select 45960,'Jacqueline','Long',0 union
select 46058,'Adam','Rivera',0 union
select 46630,'Billy','Coleman',0 union
select 46937,'Juan','Ward',0 union
select 47953,'Frank','Martinez',0 union
select 49747,'Philip','Brooks',0 union
select 50742,'Louise','Wilson',0 union
select 51850,'Victor','Doom',0 union
select 52189,'Shawn','Sanders',0 union
select 52622,'Maria','Morgan',0 union
select 54446,'Kevin','Butler',0 union
select 55146,'Mark','Jackson',0 union
select 55194,'Kathleen','Morris',0 union
select 55700,'Phillip','Edwards',0 union
select 57796,'Ernest','Adams',0 union
select 59366,'Susan','Smith',0 union
select 60959,'Andrew','Jones',0 union
select 61379,'Ryan','Williams',0 union
select 61969,'Antonio','Henderson',0 union
select 61976,'Wanda','Parker',0 union
select 62312,'Phyllis','Lopez',0 union
select 63772,'Mary','Powell',0 union
select 63859,'Maria','Young',0 union
select 64055,'Laura','Barnes',0 union
select 64063,'Mark','Coleman',0 union
select 65044,'Deborah','Hill',0 union
select 65289,'Robin','Wright',0 union
select 65441,'Arthur','Thompson',0 union
select 66386,'Chris','Henderson',0 union
select 66418,'Stephanie','Adams',0 union
select 66744,'Rachel','Edwards',0 union
select 67384,'Lawrence','Brooks',0 union
select 69101,'Ernest','Perez',0 union
select 69256,'Jacqueline','Thomas',0 union
select 70471,'Jason','Brown',0 union
select 72583,'Clarence','Brown',0 union
select 73386,'Arthur','Jones',0 union
select 73562,'Jeremy','Stewart',0 union
select 73925,'Doris','Powell',0 union
select 75671,'Billy','Jones',0 union
select 76786,'Roy','Hernandez',0 union
select 77100,'Laura','Alexander',0 union
select 78000,'Jimmy','Campbell',0 union
select 78477,'Brian','Gray',0 union
select 78990,'Katherine','Morris',0 union
select 79601,'Joe','Sanders',0 union
select 80315,'Roger','Turner',0 union
select 80321,'Kimberly','Kelly',0 union
select 81108,'Willie','Jones',0 union
select 81263,'Anna','Cooper',0 union
select 81495,'Jack','Wilson',0 union
select 82244,'Douglas','Wright',0 union
select 82333,'Charles','Smith',0 union
select 82464,'Susan','Hayes',0 union
select 83038,'Phillip','Taylor',0 union
select 83620,'Carlos','Clark',0 union
select 84873,'Cheryl','Parker',0 union
select 85587,'Justin','Mitchell',0 union
select 85981,'Sharon','Collins',0 union
select 86357,'Andrew','Evans',0 union
select 86858,'Carol','Alexander',0 union
select 87013,'Patrick','Peterson',0 union
select 87416,'Jeffrey','Griffin',0 union
select 87822,'Dennis','Long',0 union
select 87978,'Christopher','Gonzalez',0 union
select 88164,'Jimmy','Parker',0 union
select 88375,'Randy','Mitchell',0 union
select 89197,'Lawrence','Anderson',0 union
select 89902,'Joe','Gonzalez',0 union
select 90534,'Joseph','Murphy',0 union
select 90649,'Helen','Morgan',0 union
select 90667,'Carl','Murphy',0 union
select 90798,'Aaron','Wilson',0 union
select 91003,'Andrea','Clark',0 union
select 91349,'Donna','Sanders',0 union
select 91520,'Keith','Rivera',0 union
select 91672,'Edward','Green',0 union
select 92389,'Amy','Ross',0 union
select 93300,'Bonnie','Johnson',0 union
select 93791,'Evelyn','Scott',0 union
select 93995,'Kevin','Morris',0 union
select 96475,'Sean','Nelson',0 union
select 96712,'Kimberly','Powell',0 union
select 97121,'Justin','Martinez',0 union
select 97216,'Dennis','Collins',0 union
select 98826,'William','Adams',0 union
select 98923,'Dennis','Reed',0 union
select 99537,'Deborah','Hernandez',0;
insert into Booking
select 'JA100','11/28/16',22050 union
select 'JA100','11/28/16',25052 union
select 'JA100','11/28/16',44459 union
select 'JA100','11/28/16',85587 union
select 'JA100','11/28/16',73562 union
select 'JA100','11/28/16',46058 union
select 'JA100','11/28/16',46630 union
select 'JA100','11/28/16',92389 union
select 'JA100','11/28/16',84873 union
select 'JA100','11/30/16',41545 union
select 'JA100','11/30/16',18166 union
select 'JA100','11/30/16',52622 union
select 'JA100','11/30/16',38861 union
select 'JA100','11/30/16',22050 union
select 'JA100','11/30/16',57796 union
select 'JA100','11/30/16',81108 union
select 'JA110','11/28/16',38003 union
select 'JA110','11/28/16',87822 union
select 'JA110','11/28/16',33850 union
select 'JA110','11/28/16',89902 union
select 'JA110','11/28/16',83620 union
select 'JA110','12/02/16',60959 union
select 'JA110','12/02/16',98923 union
select 'JA110','12/02/16',28453 union
select 'JA110','12/02/16',87416 union
select 'JA110','12/02/16',59366 union
select 'JA130','11/29/16',52622 union
select 'JA130','12/01/16',83620 union
select 'JA130','12/01/16',38351 union
select 'JA160','11/27/16',55194 union
select 'JA160','11/27/16',97121 union
select 'JA160','11/27/16',36978 union
select 'JA160','11/27/16',33850 union
select 'JA170','11/27/16',70471 union
select 'JA170','11/27/16',90667 union
select 'JA170','11/27/16',57796 union
select 'JA170','11/27/16',75671 union
select 'JA170','11/27/16',54446 union
select 'JA170','12/02/16',66418 union
select 'JA170','12/02/16',75671 union
select 'JA170','12/02/16',96475 union
select 'JA170','12/02/16',38351 union
select 'JA180','11/28/16',37716 union
select 'JA180','11/28/16',13423 union
select 'JA180','11/28/16',20287 union
select 'JA180','11/28/16',90667 union
select 'JA180','11/28/16',65044 union
select 'JA180','11/28/16',72583 union
select 'JA190','11/27/16',83038 union
select 'JA190','11/27/16',38861 union
select 'JA190','11/27/16',69256 union
select 'JA190','11/27/16',37088 union
select 'JA190','11/28/16',19308 union
select 'JA190','11/29/16',16837 union
select 'JA190','11/29/16',97121 union
select 'JA190','11/29/16',87978 union
select 'JA190','11/29/16',27004 union
select 'JA190','11/29/16',75671 union
select 'JA190','11/29/16',85587 union
select 'JA190','11/30/16',43705 union
select 'JA190','11/30/16',41648 union
select 'JA190','11/30/16',46058 union
select 'JA190','11/30/16',69256 union
select 'JA190','11/30/16',60959 union
select 'JA190','11/30/16',38351 union
select 'JA190','12/01/16',47953 union
select 'JA190','12/01/16',66386 union
select 'JA190','12/01/16',99537 union
select 'JA190','12/01/16',80315 union
select 'JA190','12/01/16',90534 union
select 'JA190','12/01/16',73386 union
select 'JA190','12/01/16',65289 union
select 'JA190','12/01/16',96712 union
select 'JA190','12/01/16',92389 union
select 'JA190','12/02/16',66744 union
select 'JA190','12/02/16',32422 union
select 'JA190','12/02/16',97121 union
select 'JA190','12/02/16',36238 union
select 'JA190','12/02/16',41648 union
select 'JA190','12/02/16',90534 union
select 'JA190','12/02/16',82244 union
select 'JA190','12/02/16',87978 union
select 'JA190','12/03/16',90798 union
select 'JA190','12/03/16',73925 union
select 'JA190','12/03/16',55700 union
select 'JA190','12/03/16',28453 union
select 'JA190','12/03/16',35059 union
select 'JA200','11/28/16',66418 union
select 'JA200','11/28/16',91349 union
select 'JA200','11/28/16',81263 union
select 'JA200','11/28/16',13230 union
select 'JA200','12/02/16',87416 union
select 'JA200','12/02/16',96475 union
select 'JA200','12/02/16',89902 union
select 'JA200','12/02/16',11696 union
select 'JA200','12/02/16',81108 union
select 'JA200','12/02/16',76786 union
select 'JA200','12/02/16',43705 union
select 'JA200','12/02/16',96712 union
select 'JA220','11/28/16',67384 union
select 'JA220','11/28/16',13423 union
select 'JA220','11/28/16',96712 union
select 'JA220','11/28/16',61976 union
select 'JA220','11/28/16',91003 union
select 'JA220','11/30/16',73386 union
select 'JA220','11/30/16',63859 union
select 'JA220','11/30/16',97121 union
select 'JA220','11/30/16',91672 union
select 'JA220','11/30/16',10839 union
select 'JA220','11/30/16',67384 union
select 'JA220','11/30/16',11790 union
select 'JA220','11/30/16',91520 union
select 'JA230','11/28/16',32422 union
select 'JA230','11/28/16',63772 union
select 'JA230','11/28/16',33913 union
select 'JA230','11/28/16',43010 union
select 'JA230','11/28/16',38861 union
select 'JA230','11/28/16',34069 union
select 'JA230','11/28/16',38602 union
select 'JA230','12/02/16',23010 union
select 'JA230','12/02/16',47953 union
select 'JA230','12/02/16',90667 union
select 'JA230','12/02/16',79601 union
select 'JA230','12/02/16',46937 union
select 'JA230','12/02/16',27004 union
select 'JA230','12/02/16',72583 union
select 'JA240','11/27/16',33913 union
select 'JA240','11/27/16',59366 union
select 'JA240','11/27/16',90798 union
select 'JA240','11/27/16',91349 union
select 'JA240','11/27/16',20287 union
select 'JA240','11/29/16',45960 union
select 'JA240','11/29/16',66744 union
select 'JA240','11/29/16',89197 union
select 'JA240','11/29/16',80321 union
select 'JA240','11/29/16',23010 union
select 'JA240','12/02/16',70471 union
select 'JA240','12/02/16',54446 union
select 'JA250','12/02/16',73562 union
select 'JA250','12/02/16',60959 union
select 'JA250','12/02/16',90534 union
select 'JA260','11/29/16',47953 union
select 'JA260','11/29/16',88375 union
select 'JA260','11/29/16',49747 union
select 'JA260','11/29/16',63772;
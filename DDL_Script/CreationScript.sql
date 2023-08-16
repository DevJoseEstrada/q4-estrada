-- Drop the table project if it exists
DROP TABLE IF EXISTS Project;

-- Drop the table customer if it exists
DROP TABLE IF EXISTS Customer;

-- creation of the table customer
CREATE TABLE Customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    contact TEXT,
    creation_date TIMESTAMP
);

-- Creation of the table project with relation 0:N with customer
CREATE TABLE Project (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    descriptoin TEXT,
    creation_date TIMESTAMP,
    customer_id INT REFERENCES Customer(id) -- Establecer la relación
);
--MockUp data Customer
insert into Customer (name, contact, creation_date) values ('Juieta Duer', 'jduer0@nydailynews.com', '8/25/2022');
insert into Customer (name, contact, creation_date) values ('Terry Pes', 'tpes1@reuters.com', '8/26/2022');
insert into Customer (name, contact, creation_date) values ('Melisa Talton', 'mtalton2@nationalgeographic.com', '5/31/2023');
insert into Customer (name, contact, creation_date) values ('Claiborne Darnody', 'cdarnody3@mit.edu', '5/2/2023');
insert into Customer (name, contact, creation_date) values ('Leland Aidler', 'laidler4@ameblo.jp', '2/4/2023');
insert into Customer (name, contact, creation_date) values ('Dre Chopin', 'dchopin5@reddit.com', '4/4/2023');
insert into Customer (name, contact, creation_date) values ('Dmitri Sanham', 'dsanham6@lycos.com', '7/18/2023');
insert into Customer (name, contact, creation_date) values ('Charleen Cardwell', 'ccardwell7@lycos.com', '6/20/2023');
insert into Customer (name, contact, creation_date) values ('Ryon Thew', 'rthew8@blogger.com', '10/7/2022');
insert into Customer (name, contact, creation_date) values ('Gaven Batiste', 'gbatiste9@is.gd', '8/31/2022');
insert into Customer (name, contact, creation_date) values ('Darnell Kellar', 'dkellara@multiply.com', '3/12/2023');
insert into Customer (name, contact, creation_date) values ('Merwyn Souten', 'msoutenb@qq.com', '8/29/2022');
insert into Customer (name, contact, creation_date) values ('Blaire Fawley', 'bfawleyc@meetup.com', '2/1/2023');
insert into Customer (name, contact, creation_date) values ('Elyssa Featonby', 'efeatonbyd@marketwatch.com', '5/25/2023');
insert into Customer (name, contact, creation_date) values ('Anatola Hellsdon', 'ahellsdone@posterous.com', '10/20/2022');
insert into Customer (name, contact, creation_date) values ('Desi Maciaszczyk', 'dmaciaszczykf@fda.gov', '7/6/2023');
insert into Customer (name, contact, creation_date) values ('Alano Ledford', 'aledfordg@phoca.cz', '6/24/2023');
insert into Customer (name, contact, creation_date) values ('Kermy Janusik', 'kjanusikh@newyorker.com', '9/13/2022');
insert into Customer (name, contact, creation_date) values ('Waite Durston', 'wdurstoni@yolasite.com', '5/9/2023');
insert into Customer (name, contact, creation_date) values ('Gerek Pomfrett', 'gpomfrettj@vistaprint.com', '1/16/2023');
insert into Customer (name, contact, creation_date) values ('Misty Routhorn', 'mrouthornk@discuz.net', '8/31/2022');
insert into Customer (name, contact, creation_date) values ('Deanna Hartness', 'dhartnessl@usa.gov', '9/13/2022');
insert into Customer (name, contact, creation_date) values ('Holden Belchem', 'hbelchemm@addtoany.com', '7/30/2023');
insert into Customer (name, contact, creation_date) values ('Estele Trusty', 'etrustyn@dailymail.co.uk', '6/22/2023');
insert into Customer (name, contact, creation_date) values ('Warde Husselbee', 'whusselbeeo@cmu.edu', '4/13/2023');
insert into Customer (name, contact, creation_date) values ('Ravid Yurkov', 'ryurkovp@guardian.co.uk', '1/5/2023');
insert into Customer (name, contact, creation_date) values ('Fara Garland', 'fgarlandq@jiathis.com', '6/12/2023');
insert into Customer (name, contact, creation_date) values ('Shellysheldon Roseblade', 'sroseblader@google.cn', '12/3/2022');
insert into Customer (name, contact, creation_date) values ('Les Petronis', 'lpetroniss@sciencedirect.com', '5/16/2023');
insert into Customer (name, contact, creation_date) values ('Loy Brasted', 'lbrastedt@boston.com', '3/25/2023');
--MockUp data Project
insert into Project (name, descriptoin, creation_date, customer_id) values ('uk.co.timesonline.Tampflex', 'Veribet', '9/28/2022', 1);
insert into Project (name, descriptoin, creation_date, customer_id) values ('org.irdeto.denuvo', 'denuvo', '12/30/2022', 1);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.foxnews.Flexidy', 'Cookley', '8/15/2022', 1);
insert into Project (name, descriptoin, creation_date, customer_id) values ('cn.360.Fix San', 'Sonair', '11/17/2022', 2);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.blogtalkradio.Job', 'Duobam', '11/25/2022', 5);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.weather.Stim', 'Kanlam', '12/17/2022', 5);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.dell.Namfix', 'Keylex', '12/28/2022', 5);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.wordpress.Zoolab', 'Zamit', '3/24/2023', 5);
insert into Project (name, descriptoin, creation_date, customer_id) values ('gov.noaa.Job', 'Lotstring', '4/9/2023', 2);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.prweb.Ronstring', 'Bytecard', '7/31/2023', 10);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.chron.Stronghold', 'Bigtax', '11/26/2022', 4);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.freewebs.Lotlux', 'Cardify', '8/11/2022', 1);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.typepad.Tampflex', 'Opela', '2/21/2023', 1);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.webmd.Redhold', 'Veribet', '1/18/2023', 4);
insert into Project (name, descriptoin, creation_date, customer_id) values ('gl.goo.Home Ing', 'Bamity', '5/11/2023', 5);
insert into Project (name, descriptoin, creation_date, customer_id) values ('net.themeforest.Mat Lam Tam', 'Opela', '5/31/2023', 6);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.time.Domainer', 'Overhold', '11/27/2022', 11);
insert into Project (name, descriptoin, creation_date, customer_id) values ('org.gmpg.Bytecard', 'Quo Lux', '8/22/2022', 12);
insert into Project (name, descriptoin, creation_date, customer_id) values ('org.bbb.Gembucket', 'Temp', '3/24/2023', 13);
insert into Project (name, descriptoin, creation_date, customer_id) values ('au.gov.privacy.Opela', 'Keylex', '9/27/2022', 14);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.xrea.Solarbreeze', 'Veribet', '12/6/2022', 15);
insert into Project (name, descriptoin, creation_date, customer_id) values ('uk.nhs.Veribet', 'Zaam-Dox', '11/15/2022', 16);
insert into Project (name, descriptoin, creation_date, customer_id) values ('jp.jugem.Alpha', 'Namfix', '9/10/2022', 23);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.nifty.Ronstring', 'Zontrax', '10/20/2022', 20);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.hao123.Viva', 'Gembucket', '6/16/2023', 20);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.deviantart.Sonsing', 'Pannier', '1/17/2023', 20);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.stumbleupon.Alphazap', 'Latlux', '12/13/2022', 20);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.zdnet.Viva', 'Alphazap', '10/9/2022', 21);
insert into Project (name, descriptoin, creation_date, customer_id) values ('gd.is.Stringtough', 'Kanlam', '3/4/2023', 22);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.networksolutions.Bitwolf', 'Home Ing', '10/1/2022', 23);
insert into Project (name, descriptoin, creation_date, customer_id) values ('com.dell.Ronstring', 'Andalax', '5/24/2023', 24);










